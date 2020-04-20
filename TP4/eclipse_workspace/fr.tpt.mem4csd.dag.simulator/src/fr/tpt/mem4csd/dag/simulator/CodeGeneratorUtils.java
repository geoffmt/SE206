package fr.tpt.mem4csd.dag.simulator;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class CodeGeneratorUtils {

	private StringBuffer _textOutput = new StringBuffer();
	private int _ident = 0;
	
	private String _filePath;
	
	CodeGeneratorUtils(String filePath)
	{
		_filePath = filePath;
	}
	
	public void addTextNoIdent(String text)
	{
		_textOutput.append(text);
	}
	
	public void addText(String text)
	{
		addIdent();
		_textOutput.append(text);
	}
	
	public void addTextNewLine(String text)
	{
		addIdent();
		_textOutput.append(text + "\n");
	}
	
	private void addIdent()
	{
		for(int j=0;j<_ident;j++)
		{
			_textOutput.append("  ");
		}
	}
	
	public void incrementIdent()
	{
		_ident++;
	}
	
	public void decrementIdent()
	{
		_ident--;
	}
	
	public void save() throws IOException
	{
		FileWriter cFile =
	            new FileWriter(_filePath) ;
		saveFile(cFile, _textOutput.toString());
	}
	
	private void saveFile(FileWriter file,
            String ... content) throws IOException
	{
		BufferedWriter output ;
		StringBuilder sb = new StringBuilder() ;

		for(String s : content)
		{
			sb.append(s) ;
		}

		output = new BufferedWriter(file) ;
		output.write(sb.toString()) ;
		output.close() ;
	}
	
}
