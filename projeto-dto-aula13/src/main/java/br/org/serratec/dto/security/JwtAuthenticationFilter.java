package br.org.serratec.dto.security;

//public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
//	private AuthenticationManager authenticationManager;
//	private JwtUtil jwtUtil;
//
//	public JwtAuthenticationFilter(AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
//		this.authenticationManager = authenticationManager;
//		this.jwtUtil = jwtUtil;
//	}
//
//	@Override
//	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
//			throws AuthenticationException {
//		try {
//			LoginDTO login = new ObjectMapper().readValue(request.getInputStream(), LoginDTO.class);
//			UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(login.getUsername(),
//					login.getPassword(), new ArrayList<>());
//			Authentication auth = authenticationManager.authenticate(authToken);
//			return auth;
//		} catch (IOException e) {
//			throw new RuntimeException("Falha ao autenticar usuário", e);
//		}
//	}
//
//	@Override
//	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
//			Authentication authResult) throws IOException, ServletException {
//		String username = ((UserDetails) authResult.getPrincipal()).getUsername();
//		String token = jwtUtil.generateToken(username);
//		response.addHeader("Authorization", "Bearer " + token);
//		response.addHeader("access-control-expose-headers", "Authorization");
//	}
//}