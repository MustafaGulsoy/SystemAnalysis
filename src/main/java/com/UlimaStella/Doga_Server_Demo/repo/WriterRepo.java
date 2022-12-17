package com.UlimaStella.Doga_Server_Demo.repo;

import com.UlimaStella.Doga_Server_Demo.domain.Writer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WriterRepo extends JpaRepository<Writer,Long> {
    Writer findWriterById(Long id);
}
