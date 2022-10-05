package br.org.serratec.exercicio01.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.org.serratec.exercicio01.domain.Veiculo;

@RestController
@RequestMapping("/veiculo")
public class VeiculoController {

	private static List<Veiculo> veiculos = new ArrayList<Veiculo>();
	static {
		veiculos.add(new Veiculo(1L, "Volvo", "XC60"));
		veiculos.add(new Veiculo(2L, "Chevrolet", "Celta"));
		veiculos.add(new Veiculo(3L, "Fiat", "Uno"));
		veiculos.add(new Veiculo(3L, "Ford", "Fiesta"));
	}

	@GetMapping
	public List<Veiculo> listaVeiculo() {
		return veiculos;
	}

	// GET
	@GetMapping("/{id}")
	public Veiculo buscar(@PathVariable Long id) {
		for (int i = 0; i < veiculos.size(); i++) {
			if (veiculos.get(i).getId().equals(id)) {
				return veiculos.get(i);
			}
		}
		return null;
	}

	// FAZER UM POST
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Veiculo inserir(@RequestBody Veiculo veiculo) {
		veiculos.add(veiculo);
		return veiculo;
	}

	// DELETAR
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		for (int i = 0; i < veiculos.size(); i++) {
			if (veiculos.get(i).getId().equals(id)) {
				veiculos.remove(i);
				break;
			}
		}
	}

	// ATUALIZACAO
	@PutMapping("/{id}")
	public Veiculo atualizar(@RequestBody Veiculo veiculo, @PathVariable Long id) {
		for (int i = 0; i < veiculos.size(); i++) {
			if (veiculos.get(i).getId().equals(id)) {
				Veiculo veiculoLista = new Veiculo(id, veiculo.getMarca(), veiculo.getModelo());
				veiculos.set(i, veiculoLista);
				return veiculoLista;
			}
		}
		return null;
	}
}
