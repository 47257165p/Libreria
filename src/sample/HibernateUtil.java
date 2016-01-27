package sample;

/**
 * Created by 47257165p on 27/01/16.
 */
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

public class HibernateUtil
{
    private static final SessionFactory sessionFactory;
    static
    {
        try
        {
            sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
        }
        catch (HibernateException one)
        {
            System.err.println("Error en la SessionFactory: " + one);
            throw new ExceptionInInitializerError(one);
        }
    }
    public static SessionFactory getSessionFactory()
    {
        return sessionFactory;
    }
}