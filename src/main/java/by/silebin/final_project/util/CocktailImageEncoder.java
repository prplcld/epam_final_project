package by.silebin.final_project.util;

import by.silebin.final_project.entity.Cocktail;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

public class CocktailImageEncoder {

    public static void encodeImage(Cocktail cocktail) throws IOException {
        InputStream inputStream = cocktail.getIcon();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[4096];
        int bytesRead = -1;

        while ((bytesRead = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, bytesRead);
        }

        byte[] imageBytes = outputStream.toByteArray();

        cocktail.setBase64Icon(Base64.getEncoder().encodeToString(imageBytes));

        inputStream.close();
        outputStream.close();
    }
}
