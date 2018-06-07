package com.soa.other.spi;

import com.soa.other.spi.service.Command;

import java.util.ServiceLoader;

/**
 * Created by pengyunlong on 2018/5/25.
 */
public class Main {
    public static void main(String[] args) {
        ServiceLoader<Command> serviceLoader = ServiceLoader.load(Command.class);
        for (Command command:serviceLoader) {
            command.excute();
        }
    }
}
