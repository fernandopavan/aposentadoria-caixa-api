package com.aposentadoria.resources;

import com.aposentadoria.domain.dto.AporteDTO;
import com.aposentadoria.services.CaixaEletronicoService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class CaixaEletronicoResource {

    private final CaixaEletronicoService service;

    public CaixaEletronicoResource(CaixaEletronicoService service) {
        this.service = service;
    }

    @ApiOperation("Novo aporte R$")
    @PreAuthorize("hasAnyRole('BENEFICIARIO')")
    @Transactional
    @PostMapping(path = "novo-aporte")
    public ResponseEntity<AporteDTO> aporte(@Valid @RequestBody AporteDTO aporte) {
        service.novoAporte(aporte);
        return ResponseEntity.ok().build();
    }

}
