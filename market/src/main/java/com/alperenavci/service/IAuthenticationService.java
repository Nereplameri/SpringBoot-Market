package com.alperenavci.service;

import com.alperenavci.dto.AuthRequest;
import com.alperenavci.dto.AuthResponse;
import com.alperenavci.dto.DtoUser;
import com.alperenavci.dto.RefreshTokenRequest;

public interface IAuthenticationService {
	
	public DtoUser register(AuthRequest input);
	
	public AuthResponse authenticate(AuthRequest input);
	
	public AuthResponse refreshToken(RefreshTokenRequest input);
}
