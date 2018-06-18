package com.dao;

import com.entity.Guest;
import com.entity.Invoice;

public interface InvoiceDao {
	
	public void AddInvoice(Invoice invoice, Guest guest);

	public void RemoveInvoice();

	public void UodateInvoice();

	public void ReadInvoice();
}
