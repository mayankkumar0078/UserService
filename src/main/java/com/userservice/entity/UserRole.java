
package com.userservice.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
/**
 * 
 * @author MAYANK
 *
 */
@Entity
@Table(name = "user_role")
public class UserRole extends NamedEntity {
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private UserRolePK userRolePK;

    public UserRolePK getUserRolePK() {
	return userRolePK;
    }

    public void setUserRolePK(UserRolePK userRolePK) {
	this.userRolePK = userRolePK;
    }

}
