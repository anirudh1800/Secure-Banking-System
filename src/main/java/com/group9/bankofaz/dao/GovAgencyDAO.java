/**
 * 
 */
package com.group9.bankofaz.dao;

import com.group9.bankofaz.model.GovAgency;

/**
 * @author Anirudh Ruia Gali
 *
 */
public interface GovAgencyDAO {
	public void add(GovAgency govagency);

	public void update(GovAgency govagency);

	public void persist(GovAgency govagency);

	public void delete(GovAgency govagency);

	public GovAgency findByUsername(String username);
}
