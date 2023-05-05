package com.TB.TB.logic.logic.authorization;

import com.TB.TB.model.entity.authorization.User;
import com.TB.TB.model.entity.product.Product;

public interface CustomAuthentivationManager {

	boolean isAuthorized();

	boolean exit();

	boolean login(User user);

	User setProduct(Product product);

	int getUserId();

	String getUserName();
}
