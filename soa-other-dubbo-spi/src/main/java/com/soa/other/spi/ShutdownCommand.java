package com.soa.other.spi;

/**
 * Created by pengyunlong on 2018/5/25.
 */
public class ShutdownCommand  implements Command{
    @Override
    public void excute() {
        System.out.println("shutdown...");
    }
}
