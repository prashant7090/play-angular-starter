package models;

import javax.persistence.Entity;
import javax.persistence.Id;

import play.data.validation.Constraints.Required;

import com.avaje.ebean.Model;
import play.data.validation.ValidationError;

import java.util.ArrayList;
import java.util.List;


@Entity
public class Users extends Model{
	
	@Id
	public long id;
	@Required
	public String email;
	@Required
	public String name;
	public String mobile;
	@Required
	public String password;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public List<ValidationError> validate(){
		List<ValidationError> errors = new ArrayList<>();

		if( name == null || name.length() == 0){
			errors.add(new ValidationError("name", "Name can not be blanck"));
		}

		if( email == null || email.length() == 0){
			errors.add(new ValidationError("email", "Email can not be blanck"));
		}

		if( password == null || password.length() == 0){
			errors.add(new ValidationError("password", "Password can not be blanck"));
		}
		return errors.isEmpty() ? null:errors;
	}

		
}
