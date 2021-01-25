package com.aposentadoria.services;

import com.aposentadoria.config.AporteAMQPConfig;
import com.aposentadoria.domain.QUsuario;
import com.aposentadoria.domain.Usuario;
import com.aposentadoria.domain.dto.AporteDTO;
import com.aposentadoria.domain.enums.Perfil;
import com.aposentadoria.domain.enums.SituacaoCalculo;
import com.aposentadoria.domain.exception.AuthorizationException;
import com.aposentadoria.domain.exception.ObjectNotFoundException;
import com.aposentadoria.repositories.UsuarioRepository;
import com.aposentadoria.security.UserSS;
import com.aposentadoria.utils.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class CaixaEletronicoService {

    private final UsuarioRepository repository;
    private final RabbitTemplate rabbitTemplate;

    public CaixaEletronicoService(UsuarioRepository repository, RabbitTemplate rabbitTemplate) {
        this.repository = repository;
        this.rabbitTemplate = rabbitTemplate;
    }

    @Transactional
    public void novoAporte(AporteDTO aporte) {
        Usuario beneficiario = findByEmail(aporte.getEmail());

        try {
            repository.save(Usuario.Builder.from(beneficiario)
                    .situacao(SituacaoCalculo.PROCESSANDO)
                    .build());

            aporte.setId(beneficiario.getId());

            System.out.println("Iniciando aporte para: " + beneficiario.getNome());

            String json = new ObjectMapper().writeValueAsString(aporte);
            rabbitTemplate.convertAndSend(AporteAMQPConfig.EXCHANGE_NAME, "", json);

        } catch (Exception e) {
            repository.save(Usuario.Builder.from(beneficiario)
                    .situacao(SituacaoCalculo.ERRO)
                    .build());
            e.printStackTrace();
        }
    }

    private Usuario findByEmail(String email) {
        UserSS user = UserService.authenticated();
        if (user == null || !user.hasRole(Perfil.ADMIN) && !email.equals(user.getUsername())) {
            throw new AuthorizationException("Acesso negado");
        }

        Optional<Usuario> obj = repository.findOne(QUsuario.usuario.email.eq(email));

        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + user.getId() + ", Tipo: " + Usuario.class.getName()));
    }
}
