package org.example.archiving.util;

import io.github.cdimascio.dotenv.Dotenv;

public class EnvLoader {
    private static final Dotenv dotenv = Dotenv.configure()
            .directory("C:/Users/tyumi/archiving")
            .load();

    public static Dotenv getDotenv() {
        return dotenv;
    }
}
