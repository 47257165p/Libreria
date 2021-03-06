package sample;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;

/**
 * Created by 47257165p on 27/01/16.
 */
public class DAO {

    private Session session;
    private Transaction transaction;

    private void start() throws HibernateException
    {
        session = HibernateUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();
    }
    public ArrayList<Libro> actualizarArrayListLibros()
    {
        start();
        Query query = session.createQuery("FROM Libro");
        ArrayList <Libro> libros = (ArrayList<Libro>) query.list();
        session.close();
        return libros;
    }
    public ArrayList<Socio> actualizarArrayListSocios()
    {
        start();
        Query query = session.createQuery("FROM Socio");
        ArrayList <Socio> socios = (ArrayList<Socio>) query.list();
        session.close();
        return socios;
    }
    public ArrayList<Prestamo> actualizarArrayListPrestamos()
    {
        start();
        Query query = session.createQuery("FROM Prestamo");
        ArrayList <Prestamo> prestamos = (ArrayList<Prestamo>) query.list();
        session.close();
        return prestamos;
    }

    public void guardarLibro(Libro libro) throws HibernateException
    {
        try
        {
            start();
            session.save(libro);
            transaction.commit();
        }
        catch (HibernateException e)
        {
            transaction.rollback();
            throw new HibernateException("Error guardando Libro", e);
        }
        finally {session.close();}
    }

    public void guardarSocio(Socio socio) throws HibernateException
    {
        try
        {
            start();
            session.save(socio);
            transaction.commit();
        }
        catch (HibernateException e)
        {
            transaction.rollback();
            throw new HibernateException("Error guardando socio", e);
        }
        finally {session.close();}
    }

    public void guardarPrestamo(Prestamo prestamo) throws HibernateException
    {
        try
        {
            start();
            session.save(prestamo);
            transaction.commit();
        }
        catch (HibernateException e)
        {
            transaction.rollback();
            throw new HibernateException("Error guardando préstamo", e);
        }
        finally {session.close();}
    }

    public boolean borrarLibro(Libro libro)
    {
        try
        {
            start();
            session.delete(libro);
            transaction.commit();
            return true;
        }
        catch (Exception e)
        {
            transaction.rollback();
            return false;
        }
        finally {
            session.close();
        }
    }
    public boolean borrarSocio(Socio socio)
    {
        try
        {
            start();
            session.delete(socio);
            transaction.commit();
            return true;
        }
        catch (Exception e)
        {
            transaction.rollback();
            return false;
        }
        finally {
            session.close();
        }
    }
    public void borrarBasseDatos()
    {
        start();
        session.createQuery("DELETE FROM Libro ").executeUpdate();
        session.createQuery("DELETE FROM Prestamo ").executeUpdate();
        session.createQuery("DELETE FROM Socio ").executeUpdate();
        transaction.commit();
        session.close();
    }
    public void modificarLibro(Libro libro)
    {
        start();
        session.update(libro);
        transaction.commit();
        session.close();
    }
    public void modificarSocio(Socio socio)
    {
        start();
        session.update(socio);
        transaction.commit();
        session.close();
    }
}
