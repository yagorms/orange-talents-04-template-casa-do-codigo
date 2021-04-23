package br.com.zupacademy.yago.casadocodigo.validator;

import br.com.zupacademy.yago.casadocodigo.form.AutorForm;
import br.com.zupacademy.yago.casadocodigo.orm.Autor;
import br.com.zupacademy.yago.casadocodigo.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

@Component
public class ProibeEmailDuplicadoAutorValidator implements Validator {

    @Autowired
    private AutorRepository autorRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return AutorForm.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (errors.hasErrors()){
            return;
        }
        AutorForm form = (AutorForm) target;

        Optional<Autor> possivelAutor = autorRepository.findByEmail(form.getEmail());

        if(possivelAutor.isPresent()){
            errors.rejectValue("email", null, "Já existe um outro autor com o mesmo email " + form.getEmail());
        }
    }
}
