package com.userservice.repository.policy;
/**
 * @author MAYANK
 */
import com.userservice.policy.UserPasswordPolicy;

public interface PasswordPolicyRepository {
	UserPasswordPolicy getDefaultPasswordPolicy();

	UserPasswordPolicy getPasswordPolicy();
}
