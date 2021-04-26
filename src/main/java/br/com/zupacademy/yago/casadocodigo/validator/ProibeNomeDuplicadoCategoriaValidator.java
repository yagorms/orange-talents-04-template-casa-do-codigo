/*
package br.com.zupacademy.yago.casadocodigo.validator;

import br.com.zupacademy.yago.casadocodigo.form.CategoriaForm;
import br.com.zupacademy.yago.casadocodigo.orm.Categoria;
import br.com.zupacademy.yago.casadocodigo.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

@Component
public class ProibeNomeDuplicadoCategoriaValidator implements Validator {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Override
    public boolean supports(Class<?> clazz){
        return CategoriaForm.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors){
        if (errors.hasErrors()){
            return;
        }
        CategoriaForm form = (CategoriaForm) target;

        Optional<Categoria> possivelCategoria = categoriaRepository.findByNome(form.getNome());

        if(possivelCategoria.isPresent()){
            errors.rejectValue("nome", null, "Já existe uma outra categoria com o mesmo nome " + form.getNome());
        }

    }
}
*/