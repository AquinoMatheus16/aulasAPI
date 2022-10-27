package br.org.serratec.ecommerce.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.org.serratec.ecommerce.domain.Categoria;
import br.org.serratec.ecommerce.repository.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepository;

	public List<Categoria> findAll() {
		return categoriaRepository.findAll();
	}

	public Categoria alterarDto(Categoria funcionario) {
		Categoria categoriaDto = new Categoria();
		categoriaDto.setNome(categoriaDto.getNome());
		categoriaDto.setCategoria(categoriaDto.getCategoria());
		return categoriaDto;
	}

	public Optional<Categoria> findById(Long id) {
		return categoriaRepository.findById(id);
	}

	@Transactional
	public void deleteById(Long id) {
		categoriaRepository.deleteById(id);
	}

	public Categoria inserir(Categoria categoria, MultipartFile file) throws IOException {
		categoria = categoriaRepository.save(categoria);
		return alterarDto(categoria);
	}

	public Categoria save(@Valid Categoria categoria) {
		return categoriaRepository.save(categoria);
	}
}
