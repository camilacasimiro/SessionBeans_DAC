package br.edu.ifpb.convert;

import br.edu.ifpb.domain.Produto;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import java.math.BigDecimal;

@FacesConverter(value = "convert.produto", forClass = Produto.class)
public class ConverterProduto implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null) return null;
        Produto produto = new Produto(-1, value, 0.0);
        return produto;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) return null;
        String produto = value.toString();

        return (produto);
    }

}

