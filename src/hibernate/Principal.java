package hibernate;

import hibernate.Asignatura.Asignatura;
import hibernate.CorreoElectronico.CorreoElectronico;
import hibernate.Direccion.Direccion;
import hibernate.Profesor.Profesor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class Principal {
    static BufferedReader br =
            new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) {

        try {
            menu();
        } catch (IOException e) {
            System.out.println("Error al leer desde teclado");
        }


        // CRUD
        // INSERTAR
        /*Profesor profesor = new Profesor(
                "Pedro",
                "Zarco",
                "García");
        session.persist(profesor);
         */

        // LEER
        //Profesor miProfesor = session.get(Profesor.class,1);
        //System.out.println(miProfesor);

        // ACTUALIZAR
        /*session.merge(new Profesor(
                1,
                "Santiago",
                "Segura",
                "Salcedo"));
*/

        // BORRAR
        /*session.remove(new Profesor(
                1,
                null,
                null,
                null));
         */








    }

    private static void menu() throws IOException {
        SessionFactory sessionFactory =
                new Configuration()
                        .configure()
                        .buildSessionFactory();
        Session session = null;
        String respuesta = "";
        System.out.println("Bienvenido al programa gestor de profesores de Progresa.");
        do{
            System.out.println("Selecciona la opción que deseas realizar (1 2 3 4 5)\n" +
                    "1. Insertar.\n" +
                    "2. Modificar.\n" +
                    "3. Leer.\n" +
                    "4. Borrar.\n" +
                    "5. Consultar.\n" +
                    "6. Salir");
            respuesta = br.readLine();

            boolean b = respuesta.equals("1") ||
                    respuesta.equals("2") ||
                    respuesta.equals("3") ||
                    respuesta.equals("4") ||
                    respuesta.equals("5");
            if(b){
                session = sessionFactory.openSession();
                session.beginTransaction();
            }
            switch (respuesta){
                case "1" -> menuInsertar(session);
                case "2" -> modificarProfesor(session);
                case "3" -> leerProfesor(session);
                case "4" -> borrarProfesor(session);
                case "5" -> consultasHQL(session);
                default -> {
                    if(!respuesta.equals("6")) System.out.println("La respuesta tiene que ser 1, 2, 3, 4 o 5.");
                }
            }
            if(b) {
                session.getTransaction().commit();
                session.close();
            }
        }while (!respuesta.equals("6"));
    }

    private static void consultasHQL(Session session) {
        List<Profesor> profesors=
                session.createQuery(
                        "from Profesor",
                        Profesor.class).list();
        System.out.println("********* LISTA DE PROFESORES *********");
        for (Profesor p:profesors) {
            System.out.println(p);
            System.out.println("***********************");
        }
        System.out.println("********* FIN DE LA LISTA *********");
    }

    private static void menuInsertar(Session session) throws IOException {
        boolean salir = false;
        String respuesta ="";

        do{
            System.out.println("Seleccione que elemento desea insertar: (1 2 3");
            System.out.println("1. Insertar profesor");
            System.out.println("2. Insertar asignaturas");
            System.out.println("3. Insertar correos electrónicos");
            System.out.println("4. Salir");
            respuesta= br.readLine();
            switch (respuesta){
                case "1" -> {
                    insertarProfesor(session);
                    salir = true;
                }
                case "2" -> {
                    insertarAsignaturas(session);
                    salir = true;
                }
                case "3" -> {
                    insertarCorreosElectronicos(session);
                    salir = true;
                }
                default -> {
                    if (!respuesta.equals("4")){
                        System.out.println("La respuesta tiene que ser" +
                                "1, 2 3 o 4");
                    }else {
                        salir = true;
                    }
                }
            }
        }while (!salir);
    }

    private static void insertarCorreosElectronicos(Session session) throws IOException {
            List<CorreoElectronico> correos = new ArrayList<>();
            String respuesta = "";
            Profesor profesor = null;
            System.out.println("Dime el id del profesor");
            int id = Integer.parseInt(br.readLine());
            profesor = session.get(Profesor.class, id);
            System.out.println(profesor);
            System.out.println("Este es el profesor.");
            do{
                System.out.println("Dime el id del correo electrónico");
                int idCorreo = (10*id) + Integer.parseInt(br.readLine());
                System.out.println("Dime el correo electrónico");
                String nombre = br.readLine();
                CorreoElectronico newCorreo = new CorreoElectronico(idCorreo, nombre,profesor);
                correos.add(newCorreo);
                System.out.println("Desea añadir otro correo? Si para confirmar");
                respuesta = br.readLine();
            }while (respuesta.equalsIgnoreCase("si"));
            profesor.setCorreoElectronicos(correos);
            session.merge(profesor);
    }

    private static void insertarAsignaturas(Session session) throws IOException {
        Set<Asignatura> asignaturas = new HashSet<>();
        String respuesta = "";
        Profesor profesor = null;
        System.out.println("Dime el id del profesor");
        int id = Integer.parseInt(br.readLine());
        profesor = session.get(Profesor.class, id);
        System.out.println(profesor);
        System.out.println("Este es el profesor.");
        do{
            System.out.println("Dime el id de la asignatura");
            int idAsig = (10*id) + Integer.parseInt(br.readLine());
            System.out.println("Dime el nombre de la asignatura");
            String nombre = br.readLine();
            System.out.println("Dime las horas de la asignatura");
            int horas = Integer.parseInt(br.readLine());
            Asignatura asignatura = new Asignatura(idAsig, nombre,horas,profesor);
            asignaturas.add(asignatura);
            System.out.println("Desea añadir otra asignatura? Si para confirmar");
            respuesta = br.readLine();
        }while (respuesta.equalsIgnoreCase("si"));
        profesor.setAsignaturas(asignaturas);
        session.merge(profesor);
    }

    private static void borrarProfesor(Session session) throws IOException {
        int id = verProfesor(session);
        System.out.println("Este es el profesor que quiere borrar? Escriba si en caso afirmativo.");
        String respuesta = br.readLine();
        if(respuesta.equalsIgnoreCase("si")){
            Profesor profesor = session.get(Profesor.class,id);
            session.remove(profesor);
        }
    }

    private static void leerProfesor(Session session) throws IOException {
        verProfesor(session);
    }

    private static int verProfesor(Session session) throws IOException {
        System.out.println("Dime el id del profesor.");
        Profesor profesor =
                session.get(
                        Profesor.class,
                        Integer.parseInt(br.readLine()));
        System.out.println(profesor);
        return profesor.getId();
    }

    private static void modificarProfesor(Session session) throws IOException {
        Profesor profesor = leerDatos(true);
        System.out.println("***** ANTES *****");
        System.out.println(session.get(Profesor.class,profesor.getId()));
        System.out.println("***** DESPUÉS *****");
        System.out.println(profesor);
        System.out.println("Desea realizar los cambios? Escriba si");
        String respuesta = br.readLine();
        if (respuesta.equalsIgnoreCase("si")){
            session.merge(profesor);
        }
    }

    private static void insertarProfesor(Session session) throws IOException {
        Profesor profesor = leerDatos(false);
        session.persist(profesor);
        System.out.println("Profesor insertado.");
    }

    private static Profesor leerDatos(boolean modificando) throws IOException {
        System.out.println("Dime el nombre");
        String nombre = br.readLine();

        System.out.println("Dime el primer apellido");
        String apellido1 = br.readLine();

        System.out.println("Dime el segundo apellido");
        String apellido2 = br.readLine();

        Direccion direccion = leerDireccion();

        if(modificando){
            System.out.println("Dime el id que quieres modificar");
            int id = Integer.parseInt(br.readLine());
            Profesor profesorActu =
                    new Profesor(id, nombre,apellido1,apellido2);
            direccion.setId(id);
            profesorActu.setDireccion(direccion);
            direccion.setProfesor(profesorActu);
            return profesorActu;
        }

        Profesor profesor =
                new Profesor(nombre,apellido1,apellido2);
        profesor.setDireccion(direccion);
        direccion.setProfesor(profesor);
        return profesor;
    }

    private static Direccion leerDireccion() throws IOException {
        System.out.println("Dime la calle");
        String calle = br.readLine();

        System.out.println("Dime la población");
        String poblacion = br.readLine();

        System.out.println("Dime la provincia");
        String provincia = br.readLine();

        return new Direccion(calle,poblacion, provincia);
    }
}
