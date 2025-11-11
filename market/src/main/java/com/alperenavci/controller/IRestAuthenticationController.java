package com.alperenavci.controller;

import com.alperenavci.dto.AuthRequest;
import com.alperenavci.dto.AuthResponse;
import com.alperenavci.dto.DtoUser;
import com.alperenavci.dto.RefreshTokenRequest;

public interface IRestAuthenticationController {
	public RootEntity<DtoUser> register(AuthRequest input);
	public RootEntity<AuthResponse> authenticate(AuthRequest input);
	public RootEntity<AuthResponse> refreshToken(RefreshTokenRequest input);
}
