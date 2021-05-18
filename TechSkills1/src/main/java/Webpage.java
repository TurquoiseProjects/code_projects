
public class Webpage {
	String myUrl;
	public Webpage(String myUrl) {
		this.myUrl=myUrl;
	}
	public boolean showHighlight(String requestedUrl) {
		//Return true if requestedUrl is the same as myUrl.
		if(requestedUrl.equals(this.myUrl))
			return true;
		
		/*Assume that a root, section, or subsection landing page always has "index.html" for its filename, and that 
		any other page does not*/
		else if(this.myUrl.contains("index.html")) {
			String myUrlSection = this.myUrl.substring(0, this.myUrl.lastIndexOf("/"));
			String requestedUrlSection = requestedUrl.substring(0, requestedUrl.lastIndexOf("/"));
			//Return true if requestedUrl is located in the same section as myUrl, but only if myUrl is a landing page.
			
			if(myUrlSection.equals(requestedUrlSection))
				return true;
			
			//Return true if myUrl is the landing page of a parent section to requestedUrl
			else if(requestedUrlSection.contains(myUrlSection))
				return true;
		}
		return false;
	}
}
