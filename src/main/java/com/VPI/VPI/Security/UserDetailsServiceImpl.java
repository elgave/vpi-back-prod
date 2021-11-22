package com.VPI.VPI.Security;

import com.VPI.VPI.Entities.UsuarioEntity;
import com.VPI.VPI.Repositories.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    IUsuarioRepository usuarioRepository;

    @Override
    public MyUserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
       Optional<UsuarioEntity> user = usuarioRepository.findByEmail(email);

       user.orElseThrow(() -> new UsernameNotFoundException("Not found: " + email));

       return user.map(MyUserDetails::new).get();
    }



}
