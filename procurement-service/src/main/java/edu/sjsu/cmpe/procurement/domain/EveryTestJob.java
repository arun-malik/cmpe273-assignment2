package edu.sjsu.cmpe.procurement.domain;

import de.spinscale.dropwizard.jobs.Job;
import de.spinscale.dropwizard.jobs.annotations.Every;
import edu.sjsu.cmpe.procurement.dto.LostInventoryDto;
import edu.sjsu.cmpe.procurement.dto.PublisherDto;
import edu.sjsu.cmpe.procurement.dto.StompDto;

@Every("300s")
public class EveryTestJob extends Job {

	@Override
	public void doJob() {
		// logic run every time and time again
		try {
			LostInventoryDto lostInv = new LostInventoryDto();
			PublisherDto publisher = new PublisherDto();

			lostInv.saveDatatoLostInventory(StompDto.getMessagefromQueue());

			if(LostInventoryDto.getLostBooksISBN().size() > 0)
			{
				String input = "{\"id\":\""+StompDto.studentID+"\",\"order_book_isbns\":"+LostInventoryDto.getLostBooksISBN()+"}";
				System.out.println(input);
				publisher.postPublisher(input);

				publisher.getPublisher();

				//123:”Restful Web Services”:”computer”:”http://goo.gl/ZGmzoJ”
				for (LostInventory item : LostInventoryDto.getNewbooks()){
					String msg = item.getBookISBN() +":\""+ item.getTitle() +"\":\""+ item.getCategory().toLowerCase() +"\":\""+ item.getCoverimage()+"\"";
					String cat = item.getCategory().toLowerCase();
					System.out.println(msg);
					StompDto.publishBooksRToTopic(msg,cat);
					//break;
				}
			}


		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}