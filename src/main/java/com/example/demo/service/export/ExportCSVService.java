package com.example.demo.service.export;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dto.ClientDTO;

@Service
public class ExportCSVService {
	
	public void export(Writer printWriter, List<ClientDTO> clients) throws IOException{
		printWriter.write("Hello World");
	}

}
