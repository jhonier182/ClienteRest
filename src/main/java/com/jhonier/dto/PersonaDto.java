package com.jhonier.dto;

public class PersonaDto {

    private String documento;
    private String nombre;
    private String telefono;
    private int edad;
    private String profesion;
    private String password;
    private int tipo;

    public PersonaDto() {}

    public PersonaDto(String documento, String nombre, String telefono, int edad, String profesion, String password, int tipo) {
        super();
        this.documento = documento;
        this.nombre = nombre;
        this.telefono = telefono;
        this.edad = edad;
        this.profesion = profesion;
        this.password = password;
        this.tipo = tipo;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getProfesion() {
        return profesion;
    }

    public void setProfesion(String profesion) {
        this.profesion = profesion;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "PersonaDto{" + "documento=" + documento + ", nombre=" + nombre + ", telefono=" + telefono + ", edad=" + edad + ", profesion=" + profesion + ", password=" + password + ", tipo=" + tipo + '}';
    }
    
    
}
