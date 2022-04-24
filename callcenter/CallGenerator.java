package callcenter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class CallGenerator
    implements Runnable
{
// ------------------------------ FIELDS ------------------------------

    private SimpleDateFormat formatter;

    private Random random;

    private boolean running;

    private final int CALLS_QTY = 10;

    private int counterCalls = 0;

// --------------------------- CONSTRUCTORS ---------------------------

    public CallGenerator()
    {
        random = new Random();
        formatter = new SimpleDateFormat( "HH:mm:ss" );
    }

// ------------------------ INTERFACE METHODS ------------------------

// --------------------- Interface Runnable ---------------------

    @Override
    public void run()
    {
        while ( running )
        //for (int i = 0; i < CALLS_QTY; i++)
        {
            if (counterCalls < CALLS_QTY) {
                int duration = random.nextInt(5) + 1;
                //if ( duration > 1 )
                //{
                log("Create a call with duration " + duration + " minutes");
                CallQueue.queueCall(duration);
                counterCalls++;
                sleep();
                //}
            }
        }
    }

// -------------------------- OTHER METHODS --------------------------

    public void start()
    {
        running = true;
        new Thread( this ).start();
    }

    public void stop()
    {

        running = false;
        System.out.println("Simulation stopped");
    }

    private void log( String s )
    {
        System.out.println( "[" + formatter.format( new Date() ) + "][CallGenerator] " + s );
    }

    private void sleep()
    {
        try
        {
            int sleep = random.nextInt( 2 * 60 );
            //пауза между звонками
            Thread.sleep( sleep * 100 );
        }
        catch ( InterruptedException e )
        {
            //есть sleep - обязаны ловить  InterruptedException
            e.printStackTrace();
        }
    }

    public int getCounterCalls() {
        return counterCalls;
    }
}
