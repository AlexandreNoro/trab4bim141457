package br.univel.uteis;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 *
 * @author Alexandre H. Noro
 * @data 17 de nov de 2016 as 21:57:45
 */

/**
 * Classe para corrigir o problema do cadastro de data com LocalDateTime no JPA.
 */
@Converter(autoApply = true) //Annotation para deixar definido que é pra realizar a conversão automaticamente.
public class LocalDateTimeAttributeConverter implements AttributeConverter<LocalDateTime, Timestamp> {


    //Converte em Timestamp no momento de persistir no banco de dados.
    @Override
    public Timestamp convertToDatabaseColumn(LocalDateTime localDateTime) {

    	if(localDateTime != null)
    		return Timestamp.valueOf(localDateTime);

    	return null;

    }

    //Converte um Timestamp em LocalDateTime quando voltar do banco para a entidade.
    @Override
    public LocalDateTime convertToEntityAttribute(Timestamp timestamp) {

    	if(timestamp != null)
    		return timestamp.toLocalDateTime();

    	return null;
    }
}