/**
 * 
 */
package com.group9.bankofaz.dao;

import com.group9.bankofaz.model.ExternalUser;
import com.group9.bankofaz.model.Pii;

/**
 * @author Anirudh Ruia Gali
 *
 */
public interface PiiDAO {
	public void add(Pii pii);

	public void update(Pii pii);

	public void persist(Pii pii);

	public void delete(Pii pii);

	public Pii findBySSN(String ssn);
	
	public Pii findBySSN(ExternalUser externaluser);

}
