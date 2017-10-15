package com.moneywizzard.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moneywizzard.dao.MoneywizardaccountDao;
import com.moneywizzard.dao.MwaccounthistoryDao;
import com.moneywizzard.dao.PersonDao;
import com.moneywizzard.model.Moneywizardaccount;
import com.moneywizzard.model.Mwaccounthistory;
import com.moneywizzard.model.Person;
import com.moneywizzard.object.MoneyWizzardAccountHistoryObj;
import com.moneywizzard.object.MoneyWizzardAccountObj;
import com.moneywizzard.services.iface.MoneyWizzardAccountService;
import com.moneywizzard.utils.MWUtils;

@Service
public class MoneyWizzardAccountServiceImpl implements MoneyWizzardAccountService {

	@Autowired
	private PersonDao personDao;

	@Autowired
	private MoneywizardaccountDao moneywizardaccountDao;
	
	@Autowired
	private MwaccounthistoryDao mwaccounthistoryDao;

	@Override
	@Transactional
	public String addMoney(MoneyWizzardAccountObj moneyWizzardAccountObj) throws Exception {
		if (moneyWizzardAccountObj != null) {

			Person person = personDao.getById(moneyWizzardAccountObj.getPerson());

			Moneywizardaccount mwa = moneywizardaccountDao.getByCriteria(person);
			if(mwa!=null){
				double bal = mwa.getAccountbalance();
				mwa.setAccountbalance(bal+moneyWizzardAccountObj.getAccountBalance());
				moneywizardaccountDao.update(mwa);
			}else{
				Moneywizardaccount moneywizardaccount = new Moneywizardaccount();				
				moneywizardaccount.setPersonid(person);
				moneywizardaccount.setId(MWUtils.generateGUID());
				moneywizardaccount.setAccountbalance(moneyWizzardAccountObj.getAccountBalance());
				moneywizardaccountDao.save(moneywizardaccount);				
			}

			return person.getId();
		}

		return null;
	}

	@Override
	@Transactional
	public Double getCurrentBalance(String personId) throws Exception {
		if (personId != null) {

			Person person = personDao.getById(personId);
			
			Moneywizardaccount moneywizardaccount = moneywizardaccountDao.getByCriteria(person);
			if(moneywizardaccount!=null){
				return moneywizardaccount.getAccountbalance();				
			}else{
				return 0.0;
			}


		}

		return null;
	}

	@Override
	@Transactional
	public List<MoneyWizzardAccountObj> getAccountHistory(String personId) throws Exception {
		List<MoneyWizzardAccountObj> historyObj = new ArrayList<>();
		
		Person person = personDao.getById(personId);
		if(person!=null){
			Mwaccounthistory mwaccounthistory = new Mwaccounthistory();
			mwaccounthistory.setSenderpersonid(person);
			List<Mwaccounthistory> mwaccounthistorys =  mwaccounthistoryDao.getByCriteria(mwaccounthistory);
			if(mwaccounthistorys!=null && mwaccounthistorys.size()>0){
				for(Mwaccounthistory history:mwaccounthistorys){
					MoneyWizzardAccountObj accountObj = new MoneyWizzardAccountObj();
					accountObj.setAccountBalance(history.getAmount());
					accountObj.setDate(history.getDate());
					if(history.getSenderpersonid().getId().equalsIgnoreCase(personId)){
						accountObj.setPersonId(history.getRecieverpersonid().getId());
						accountObj.setPersonName(history.getRecieverpersonid().getName());
						accountObj.setReceive(false);
					}else{
						accountObj.setPersonId(history.getSenderpersonid().getId());
						accountObj.setPersonName(history.getSenderpersonid().getName());
						accountObj.setReceive(true);
					}
					historyObj.add(accountObj);
				}
			}
		}
		return historyObj;
	}

	@Override
    @Transactional
    public String transferMoney(MoneyWizzardAccountHistoryObj moneyWizzardAccountHistoryObj) throws Exception {
        
        String trasnferStatus = "Please try again!";
        Double senderBalance = 0.0;
        Double receiverBalance = 0.0;

        if (moneyWizzardAccountHistoryObj != null) {
            
            // get Sender and Receiver person objects
            Mwaccounthistory mwaccounthistory = new Mwaccounthistory();
            Person senderPerson = personDao.getById(moneyWizzardAccountHistoryObj.getSenderPersonId());
            Person receiverPerson = personDao.getById(moneyWizzardAccountHistoryObj.getRecieverPersonId());
            
            // set mwaccounthistory table attributes
            
            mwaccounthistory.setSenderpersonid(senderPerson);
            mwaccounthistory.setRecieverpersonid(receiverPerson);
            mwaccounthistory.setId(MWUtils.generateGUID());
            mwaccounthistory.setAmount(moneyWizzardAccountHistoryObj.getAmount());
            mwaccounthistory.setComment(moneyWizzardAccountHistoryObj.getComment());
            mwaccounthistory.setDate(new Date());

            // save mwaccounthistory
            mwaccounthistoryDao.save(mwaccounthistory);

            
            // update the balances of sender and receiver
            Moneywizardaccount moneywizardaccountSender = moneywizardaccountDao.getByCriteria(senderPerson);

            senderBalance = moneywizardaccountSender.getAccountbalance();
            senderBalance = senderBalance - moneyWizzardAccountHistoryObj.getAmount();

            moneywizardaccountSender.setAccountbalance(senderBalance);

            Moneywizardaccount moneywizardaccountReceiver = moneywizardaccountDao.getByCriteria(receiverPerson);

            receiverBalance = moneywizardaccountReceiver.getAccountbalance();
            receiverBalance = receiverBalance + moneyWizzardAccountHistoryObj.getAmount();
            moneywizardaccountReceiver.setAccountbalance(receiverBalance);

            moneywizardaccountDao.update(moneywizardaccountSender);
            moneywizardaccountDao.update(moneywizardaccountReceiver);

            trasnferStatus = "Transaction Successful!";

        }

        return trasnferStatus;
    }

}