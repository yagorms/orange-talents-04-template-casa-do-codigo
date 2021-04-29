package br.com.zupacademy.yago.casadocodigo.validator;

import br.com.zupacademy.yago.casadocodigo.form.ClienteForm;
import br.com.zupacademy.yago.casadocodigo.orm.Cliente;
import br.com.zupacademy.yago.casadocodigo.orm.Estado;
import br.com.zupacademy.yago.casadocodigo.orm.Pais;
import br.com.zupacademy.yago.casadocodigo.repository.ClienteRepository;
import br.com.zupacademy.yago.casadocodigo.repository.EstadoRepository;
import br.com.zupacademy.yago.casadocodigo.repository.PaisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.List;
import java.util.Optional;

@Component
public class VerificaSePaisTemEstadoValidator implements Validator {

    @Autowired
    private EstadoRepository estadoRepository;

    @Autowired
    private PaisRepository paisRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return ClienteForm.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (errors.hasErrors()){
            return;
        }

        ClienteForm form = (ClienteForm) target;


        Optional<Pais> possivelpais = paisRepository.findById(form.getIdPais());
        List<Estado> possivelEstado = estadoRepository.findByPaisId(form.getIdPais());
        Optional<Estado> pEstado = estadoRepository.findByIdAndPaisId(form.getIdEstado(), form.getIdPais());

        if (possivelpais.isEmpty()){
            errors.rejectValue("idPais", null, "País não existe");
        }

        if (!pEstado.isPresent() && form.getIdEstado() !=null){
            errors.rejectValue("idPais", null, "Estado não pertence ao país");
        }

        if (!possivelEstado.isEmpty() && form.getIdEstado() == null){
            errors.rejectValue("idPais", null, "Selecione um Estado válido para o País");
        }


    }
}
