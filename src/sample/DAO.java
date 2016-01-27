package sample;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Created by 47257165p on 27/01/16.
 */
public class DAO {

    private Session session;
    private Transaction transaction;

    public void saveBook(Libro libro) throws HibernateException
    {
        try
        {
            start();
            session.save(libro);
            transaction.commit();
        }
        catch (HibernateException one)
        {
            transaction.rollback();
            throw new HibernateException("Error guardando Libro", one);
        }
        finally {session.close();}
    }
    private void start() throws HibernateException
    {
        session = HibernateUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();
    }

}
