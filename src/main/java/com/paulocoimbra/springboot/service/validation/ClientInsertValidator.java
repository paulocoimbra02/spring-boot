package com.paulocoimbra.springboot.service.validation;

import com.paulocoimbra.springboot.domain.enums.ClientType;
import com.paulocoimbra.springboot.dto.ClientNewDTO;
import com.paulocoimbra.springboot.resources.exception.FieldMessage;
import com.paulocoimbra.springboot.service.validation.utils.BR;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class ClientInsertValidator implements ConstraintValidator<ClientInsert, ClientNewDTO> {
    @Override
    public void initialize(ClientInsert ann) {
    }

    @Override
    public boolean isValid(ClientNewDTO objDto, ConstraintValidatorContext context) {
        List<FieldMessage> list = new ArrayList<>();

        if(objDto.getClientType().equals(ClientType.PESSOAFISICA.getCod()) && !BR.isValidCPF(objDto.getCpfOrCnpj())){
            list.add(new FieldMessage("cpfOrCnpj", "Invalid CPF"));
        }
        if(objDto.getClientType().equals(ClientType.PESSOAJURIDICA.getCod()) && !BR.isValidCNPJ(objDto.getCpfOrCnpj())){
            list.add(new FieldMessage("cpfOrCnpj", "Invalid CNPJ"));
        }

        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage())
                    .addPropertyNode(e.getFieldName()).addConstraintViolation();
        }
        return list.isEmpty();
    }
}
