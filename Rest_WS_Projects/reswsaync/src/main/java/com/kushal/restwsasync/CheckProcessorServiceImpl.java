package com.kushal.restwsasync;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.container.AsyncResponse;

import org.springframework.stereotype.Service;

import com.kushal.restwsasync.model.ChecksList;

@Service
public class CheckProcessorServiceImpl implements CheckProcessor {

	@Override
	public void processCheck(AsyncResponse response, ChecksList checksList) {
		// Logic to implement the check processing

		new Thread() {
			public void run() {
				if (null == checksList || null == checksList.getChecks() || checksList.getChecks().size() == 0) {
					response.resume(new BadRequestException());
				}
				response.resume(true);
			}
		}.start();
	}
}
