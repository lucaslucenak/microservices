package br.edu.uepb.example.firstmicroservice.dtos;

import java.io.Serializable;

import br.edu.uepb.example.firstmicroservice.models.RoleModel;

public class RoleDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String authority;

    public RoleDTO() {
    }

    public RoleDTO(Long id, String authority) {
        this.id = id;
        this.authority = authority;
    }

    public RoleDTO(RoleModel role) {
        id = role.getId();
        authority = role.getAuthority();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

}
