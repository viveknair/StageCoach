package line.meta_information;

import line.Line; 
import line.LineType;

public class MetaInformation extends Line {

	public MetaInformation(String description) {
		super(LineType.METAINFORMATION);
		
		this.description = description;
	}	
}