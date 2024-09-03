package converter;

import dao.RoleDAO;
import entity.Role;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;
import jakarta.inject.Inject;

@FacesConverter("roleConverter")
public class RoleConverter implements Converter {

	 private RoleDAO roleDAO = new RoleDAO();

	 @Override
	 public Object getAsObject(FacesContext context, UIComponent component, String value) {
	     if (value == null || value.isEmpty()) {
	         return null;
	     }

	     try {
	         int id = Integer.parseInt(value);
	         return roleDAO.getById(id);
	     } catch (NumberFormatException e) {
	         throw new RuntimeException("roleid format düzelt", e);
	     }
	 }

	 @Override
	 public String getAsString(FacesContext context, UIComponent component, Object value) {
	     if (value == null || !(value instanceof Role)) {
	         return "";
	     }

	     Role role = (Role) value;
	     return String.valueOf(role.getId());
	 }

}
