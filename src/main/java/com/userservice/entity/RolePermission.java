/*
 * Copyright 2014 Ranjan Kumar
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.userservice.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
/**
 * 
 * @author MAYANK
 *
 */
@Entity
@Table(name = "role_permission")
public class RolePermission extends NamedEntity {
    private static final long serialVersionUID = 1L;

    @ManyToOne
    @JoinColumn(name = "permission_id")
    private Permission permission;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    public Permission getPermission() {
	return permission;
    }

    public void setPermission(Permission permission) {
	this.permission = permission;
    }

    public Role getRole() {
	return role;
    }

    public void setRole(Role role) {
	this.role = role;
    }
}
