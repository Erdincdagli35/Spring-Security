package com.ed.springsecurity.constant;

import com.google.common.collect.Sets;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

public enum Role {
    STUDENT(Sets.newHashSet()),
    ADMINTRAINEE(Sets.newHashSet(Permisson.COURSE_READ, Permisson.STUDENT_READ)),
    ADMIN(Sets.newHashSet(Permisson.COURSE_READ, Permisson.COURSE_WRITE, Permisson.STUDENT_READ, Permisson.STUDENT_WRITE));

    private final Set<Permisson> permissons;

    Role(Set<Permisson> permissions) {
        this.permissons = permissions;
    }

    public Set<Permisson> getPermissons() {
        return permissons;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthority() {
        Set<SimpleGrantedAuthority> permissons = getPermissons().stream()
                .map(permisson -> new SimpleGrantedAuthority(permisson.getPermisson()))
                .collect(Collectors.toSet());
        permissons.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return permissons;
    }
}
