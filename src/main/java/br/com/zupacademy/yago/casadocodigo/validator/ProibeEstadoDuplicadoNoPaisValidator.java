package br.com.zupacademy.yago.casadocodigo.validator;

import br.com.zupacademy.yago.casadocodigo.form.EstadoForm;
import br.com.zupacademy.yago.casadocodigo.orm.Estado;
import br.com.zupacademy.yago.casadocodigo.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

@Component
public class ProibeEstadoDuplicadoNoPaisValidator implements Validator {

    @Autowired
    private EstadoRepository estadoRepository;

    @Override
    public boolean supports(Class<?> clazz){
        return EstadoForm.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors){
        if (errors.hasErrors()){
            return;
        }

        EstadoForm form = (EstadoForm) target;

        Optional<Estado> possivelEstado = estadoRepository.findByNomeAndPaisId(form.getNome(),form.getIdPais());

        if(possivelEstado.isPresent()){
            errors.rejectValue("nome", null, "Pais Id: " +form.getIdPais()+" já possui o estado " +form.getNome());
        }
    }

}