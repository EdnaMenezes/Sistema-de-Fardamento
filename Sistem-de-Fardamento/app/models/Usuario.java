package models;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import play.data.validation.Email;
import play.data.validation.MinSize;
import play.data.validation.Required;
import play.db.jpa.Model;
import play.libs.Crypto;

@Entity
public class Usuario extends Model {

	public String nome;
	public String email;
	public String senha;
	public int nivel;
	
	public void setSenha(String s){
		//123456
		//4QrcOUm6Wau+VuBX8g+IPg==
		senha = Crypto.passwordHash(s);
	}
	
}
