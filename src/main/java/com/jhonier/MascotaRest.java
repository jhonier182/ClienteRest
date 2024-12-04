package com.jhonier;

import com.jhonier.dto.MascotaDto;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

public class MascotaRest {

    String URL = "http://localhost:8080/mascotas";

    public static void main(String[] args) {
        MascotaRest clienteRest = new MascotaRest();
        clienteRest.llamarServicioMascota();
    }

    private void llamarServicioMascota() {
        consultarSaludo();
        consultarMascotaPorId(1);
         consultarListaMascotas();
         registrarMascota(new MascotaDto(999, "poker", "Perro", 5, 111));
         actualizarMascota(1, new MascotaDto(1, "Max", "Perro", 6, 222));
         eliminarMascota(2);
        obtenerMascotasPorPersonaId(111);
    }

    public void consultarSaludo() {
        Client cliente = ClientBuilder.newClient();
        try {
            WebTarget webTarget = cliente.target(URL).path("hola");
            Response response = webTarget.request(MediaType.TEXT_PLAIN).get();
            if (response.getStatus() == Response.Status.OK.getStatusCode()) {
                String saludo = response.readEntity(String.class);
                System.out.println("Saludo del servidor: " + saludo);
            } else {
                System.out.println("Error al consultar el saludo. Código HTTP: " + response.getStatus());
            }
        } finally {
            cliente.close();
        }
    }

    public void consultarMascotaPorId(int id) {
        Client cliente = ClientBuilder.newClient();
        try {
            WebTarget webTarget = cliente.target(URL).path("mascota/" + id);
            Response response = webTarget.request(MediaType.APPLICATION_JSON).get();
            if (response.getStatus() == Response.Status.OK.getStatusCode()) {
                MascotaDto mascota = response.readEntity(MascotaDto.class);
                System.out.println("Mascota encontrada: " + mascota);
            } else {
                System.out.println("Mascota no encontrada. Código HTTP: " + response.getStatus());
            }
        } finally {
            cliente.close();
        }
    }

    public void consultarListaMascotas() {
        Client cliente = ClientBuilder.newClient();
        try {
            WebTarget webTarget = cliente.target(URL).path("mascota-lista");
            Response response = webTarget.request(MediaType.APPLICATION_JSON).get();
            if (response.getStatus() == Response.Status.OK.getStatusCode()) {
                List<MascotaDto> mascotas = response.readEntity(new GenericType<List<MascotaDto>>() {});
                System.out.println("Lista de mascotas: " + mascotas);
            } else {
                System.out.println("Error al consultar lista de mascotas. Código HTTP: " + response.getStatus());
            }
        } finally {
            cliente.close();
        }
    }

    public void registrarMascota(MascotaDto mascotaDto) {
        Client cliente = ClientBuilder.newClient();
        try {
            WebTarget webTarget = cliente.target(URL).path("registrar");
            Response response = webTarget.request(MediaType.APPLICATION_JSON)
                    .post(Entity.entity(mascotaDto, MediaType.APPLICATION_JSON));
            if (response.getStatus() == Response.Status.OK.getStatusCode()) {
                MascotaDto mascotaGuardada = response.readEntity(MascotaDto.class);
                System.out.println("Mascota registrada: " + mascotaGuardada);
            } else {
                System.out.println("Error al registrar mascota. Código HTTP: " + response.getStatus());
            }
        } finally {
            cliente.close();
        }
    }

    public void actualizarMascota(int id, MascotaDto mascotaDto) {
        Client cliente = ClientBuilder.newClient();
        try {
            WebTarget webTarget = cliente.target(URL).path("actualizar/" + id);
            Response response = webTarget.request(MediaType.APPLICATION_JSON)
                    .put(Entity.entity(mascotaDto, MediaType.APPLICATION_JSON));
            if (response.getStatus() == Response.Status.OK.getStatusCode()) {
                MascotaDto mascotaActualizada = response.readEntity(MascotaDto.class);
                System.out.println("Mascota actualizada: " + mascotaActualizada);
            } else {
                System.out.println("Error al actualizar mascota. Código HTTP: " + response.getStatus());
            }
        } finally {
            cliente.close();
        }
    }

    public void eliminarMascota(int id) {
        Client cliente = ClientBuilder.newClient();
        try {
            WebTarget webTarget = cliente.target(URL).path("eliminar/" + id);
            Response response = webTarget.request().delete();
            if (response.getStatus() == Response.Status.OK.getStatusCode()) {
                String mensaje = response.readEntity(String.class);
                System.out.println("Respuesta del servidor: " + mensaje);
            } else {
                System.out.println("Error al eliminar mascota. Código HTTP: " + response.getStatus());
            }
        } finally {
            cliente.close();
        }
    }

    public void obtenerMascotasPorPersonaId(int personaId) {
        Client cliente = ClientBuilder.newClient();
        try {
            WebTarget webTarget = cliente.target(URL).path("mascotas/persona/" + personaId);
            Response response = webTarget.request(MediaType.APPLICATION_JSON).get();
            if (response.getStatus() == Response.Status.OK.getStatusCode()) {
                List<MascotaDto> mascotas = response.readEntity(new GenericType<List<MascotaDto>>() {});
                System.out.println("Mascotas de la persona con ID " + personaId + ": " + mascotas);
            } else {
                System.out.println("Error al consultar mascotas por persona. Código HTTP: " + response.getStatus());
            }
        } finally {
            cliente.close();
        }
    }
}
