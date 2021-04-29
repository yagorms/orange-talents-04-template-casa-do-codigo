package br.com.zupacademy.yago.casadocodigo.controller;

import br.com.zupacademy.yago.casadocodigo.form.ClienteForm;
import br.com.zupacademy.yago.casadocodigo.orm.Cliente;
import br.com.zupacademy.yago.casadocodigo.repository.ClienteRepository;
import br.com.zupacademy.yago.casadocodigo.repository.EstadoRepository;
import br.com.zupacademy.yago.casadocodigo.repository.PaisRepository;
import br.com.zupacademy.yago.casadocodigo.validator.VerificaSePaisTemEstadoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private EstadoRepository estadoRepository;
    @Autowired
    private PaisRepository paisRepository;

    @Autowired
    private VerificaSePaisTemEstadoValidator verificaSePaisTemEstadoValidator;

    @InitBinder
    public void init (WebDataBinder binder) {binder.addValidators(verificaSePaisTemEstadoValidator);}

    @PostMapping
    @Transactional
    public String cadastrar(@RequestBody @Valid ClienteForm form){
        Cliente cliente = form.converter(estadoRepository, paisRepository);
        clienteRepository.save(cliente);
        return cliente.toString();
    }
}
