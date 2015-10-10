/**
 * 
 */
package com.group9.bankofaz.dao;

import com.group9.bankofaz.model.Authorizes;
import com.group9.bankofaz.model.ExternalUser;
import com.group9.bankofaz.model.InternalUser;
import com.group9.bankofaz.model.Transaction;

/**
 * @author Anirudh Ruia Gali
 *
 */

public interface AuthorizesDAO {
	public void add(Authorizes authorizes);

	public void update(Authorizes authorizes);

	public void persist(Authorizes authorizes);

	public void delete(Authorizes authorizes);

	public Authorizes findByIds(InternalUser empid, ExternalUser userid, Transaction tid);
}
