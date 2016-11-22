package br.univel.uteis;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Date;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.DateTimeConverter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Alexandre H. Noro
 * @data 21 de nov de 2016 as 19:45:11
 */

@FacesConverter(value = LocalDateTimeConverter.ID)

/**
 * Classe respons�vel por converter os valores de LocalDateTime que ser�o
 * exibidos na p�gina de consulta.
 */
public class LocalDateTimeConverter extends DateTimeConverter {

	public static final String ID = "br.univel.uteis.LocalDateTimeConverter";

	/**
	 * M�todo que realiza a convers�o dos valores da data de cadastro que ser�o
	 * exibidos na Tabela de consulta.
	 */

	@Override
	public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {

		Date date = null;
		LocalDateTime localDateTime = null;

		Object object = super.getAsObject(facesContext, uiComponent, value);

		if (object instanceof Date) {

			date = (Date) object;

			Instant instant = Instant.ofEpochMilli(date.getTime());
			localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
			return localDateTime;

		} else {

			throw new IllegalArgumentException(
					String.format("value=%s N�o foi poss�vel converter LocalDateTime, resultado super.getAsObject=%s",
							value, object));
		}

	}

	/**
	 * M�todo que realiza a convers�o do LocalDateTime em String.
	 */
	@Override
	public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object value) {

		if (value == null)
			return super.getAsString(facesContext, uiComponent, value);

		if (value instanceof LocalDateTime) {

			LocalDateTime localDateTime = (LocalDateTime) value;

			Instant instant = localDateTime.toInstant(ZoneOffset.UTC);

			Date date = Date.from(instant);

			return super.getAsString(facesContext, uiComponent, date);
		} else {

			throw new IllegalArgumentException(String.format("value=%s n�o � um LocalDateTime", value));
		}

	}
}