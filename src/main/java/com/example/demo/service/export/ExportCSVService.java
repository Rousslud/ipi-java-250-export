package com.example.demo.service.export;

import com.example.demo.dto.ClientDTO;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.persistence.Column;

@Service
public class ExportCSVService {

    public void export(Writer printWriter, List<ClientDTO> clients) throws IOException {
    	printWriter.write("Numéro de client : ;");
    	printWriter.write("Nom : ;");
        printWriter.write("Prenom : ;");
        printWriter.write("Adresse : ;");
        printWriter.write("Code postal : ;");
        printWriter.write("Ville : ;");
        printWriter.write("Email : ;");
        printWriter.write("Telephone : ;");
        printWriter.write("Date d'inscription : ;");
        printWriter.write("Ancienneté (en années) : ;");
        printWriter.write("\n");
        for (ClientDTO client : clients) {
        	printWriter.write("\"" +client.getId() + "\"");
            printWriter.write(";");
            printWriter.write("\"" +client.getNom() + "\"");
            printWriter.write(";");
            printWriter.write("\"" +client.getPrenom() + "\"");
            printWriter.write(";");
            printWriter.write("\"" +client.getAdresse() + "\"");
            printWriter.write(";");
            printWriter.write("\"" +client.getCP() + "\"");
            printWriter.write(";");
            printWriter.write("\"" +client.getVille() + "\"");
            printWriter.write(";");
            printWriter.write("\"" +client.getEmail() + "\"");
            printWriter.write(";");
            printWriter.write("\"" +client.getTelephone() + "\"");
            printWriter.write(";");
            String dateInscription = new SimpleDateFormat("dd/MM/yyyy").format((client.getDateinscription()));
            printWriter.write(dateInscription);
            printWriter.write(";");
            Date today = Calendar.getInstance().getTime();
            Long anciennete = getDateDiff(client.getDateinscription(),today, TimeUnit.DAYS);
            anciennete = anciennete / 365;
            printWriter.write(anciennete.toString());
            printWriter.write("\n");
        }

    }
    
    /**
     * Get a diff between two dates
     * @param date1 the oldest date
     * @param date2 the newest date
     * @param timeUnit the unit in which you want the diff
     * @return the diff value, in the provided unit
     */
    public static long getDateDiff(Date date1, Date date2, TimeUnit timeUnit) {
        long diffInMillies = date2.getTime() - date1.getTime();
        return timeUnit.convert(diffInMillies,TimeUnit.MILLISECONDS);
    }
}
