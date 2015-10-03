/**
 * 
 */
package com.group9.bankofaz.dao;

import com.group9.bankofaz.model.Authentication;

/**
 * @author Anirudh Ruia Gali
 *
 */

public interface AuthenticationDAO {
	public void add(Authentication authentication);

	public void update(Authentication authentication);

	public void persist(Authentication authentication);

	public void delete(Authentication authentication);

	public Authentication findAuthenticationById(String email);
}
