package com.twistlet.falcon.model.repository;

import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.twistlet.falcon.model.entity.FalconStaff;
import com.twistlet.falcon.model.entity.FalconUser;

public class FalconStaffRepositoryImplTest extends AbstractFalconRepositoryTest{
	
	@Autowired
	FalconStaffRepository falconStaffRepository;

	@PersistenceContext
	EntityManager entityManager;
	
	
	@Before
	public void init() {
		FalconUser user1, user2, user3;
		FalconStaff staff1, staff2, staff3;
		
		entityManager.persist(user1 = createNewUser("USER_1", "email1@add.com", "1", "1"));
		entityManager.persist(user2 = createNewUser("USER_2", "email2@add.com", "2", "2"));
		entityManager.persist(user3 = createNewUser("USER_3", "email3@add.com", "3", "3"));
		entityManager.persist(staff1 = createNewStaff("staff1", "87111", "ggg@gmail.com", user1));
		entityManager.persist(staff2 = createNewStaff("staff2", "86111", "yyy@yahoo.com", user1));
		entityManager.persist(staff3 = createNewStaff("staff3", "89111", "xxx@yahoo.com", user2));
		entityManager.flush();
		entityManager.clear();
	}
	
	private FalconStaff createNewStaff(String name, String nric, String email, FalconUser user) {
		FalconStaff staff = new FalconStaff();
		staff.setFalconUser(user);
		staff.setName(name);
		staff.setNric(nric);
		staff.setHpTel("0129999999");
		staff.setEmail(email);
		staff.setValid(true);
		return staff;
	}

	private FalconUser createNewUser(final String username, final String email, final String nric, final String phone) {
		final FalconUser falconUser = new FalconUser();
		falconUser.setUsername(username);
		falconUser.setPassword("x");
		falconUser.setName(username);
		falconUser.setEmail(email);
		falconUser.setNric(nric);
		falconUser.setPhone(phone);
		return falconUser;
	}
	
	@Test
	public void testFindByFalconUserNameLike(){
		final FalconUser admin = createNewUser("USER_1", "email1@add.com", "1", "1");
		final String name = "staff";
		List<FalconStaff> staffs = falconStaffRepository.findByFalconUserNameLike(admin, name);
		assertEquals(2, staffs.size());
	}
	
	@Test
	public void testFindByFalconUserNricLike(){
		final FalconUser admin = createNewUser("USER_1", "email1@add.com", "1", "1");
		final String nric = "86";
		List<FalconStaff> staffs = falconStaffRepository.findByFalconUserNricLike(admin, nric);
		assertEquals(1, staffs.size());
	}
	
	@Test
	public void testFindByFalconUserEmailLike(){
		final FalconUser admin = createNewUser("USER_1", "email1@add.com", "1", "1");
		final String email = "yahoo";
		List<FalconStaff> staffs = falconStaffRepository.findByFalconUserEmailLike(admin, email);
		assertEquals(1, staffs.size());
	}
	
	
	@Test
	public void testFindByFalconUserHpTelLike(){
		final FalconUser admin = createNewUser("USER_1", "email1@add.com", "1", "1");
		final String hpTel = "2999";
		List<FalconStaff> staffs = falconStaffRepository.findByFalconUserHpTelLike(admin, hpTel);
		assertEquals(2, staffs.size());
	}

	
	@Test
	public void testFindByFalconUserStaffLikeEmpty(){
		final FalconUser admin = createNewUser("USER_1", "email1@add.com", "1", "1");
		final FalconStaff empty = createNewStaff(null, null, "yyy@yahoo.com", admin);
		List<FalconStaff> result = falconStaffRepository.findByFalconUserStaffLike(admin, empty);
		assertEquals(1, result.size());
	}
	
	@Test
	public void testFindByFalconUserStaffLikeFull(){
		final FalconUser admin = createNewUser("USER_1", "email1@add.com", "1", "1");
		final FalconStaff staff = createNewStaff("staff1", "87111", "ggg@gmail.com", admin);
		List<FalconStaff> staffs = falconStaffRepository.findByFalconUserStaffLike(admin, staff);
		assertEquals(1, staffs.size());
	}
}
