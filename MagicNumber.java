package br.com.rackerrank.solution1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class MagicNumber {
	public static void main(String[] args) throws NumberFormatException, IOException {
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader br = new BufferedReader(new FileReader("/home/vitor/Desktop/magic-number-testcases/input/input01.txt"));
		// InputStreamReader(System.in));
		String linha = br.readLine();
		int T = Integer.parseInt(linha);
		// numero de testes
		for (int t = 0; t < T; t++) {
			Set<Integer> result = new HashSet<>();
			int n = Integer.parseInt(br.readLine());

			Set<String> list = lerStrings(br, n);

			for (String string : list) {
				// se não sei qual o numero magico...
				// considero prefixo até o meio da palavra
				String p = string.substring(0, string.length() / 2);
				// considero do meio para o fim
				String s = string.substring(string.length() / 2, string.length());

				Map<String, Integer> prefixos = criarDicionario(p);
				Map<String, Integer> sufixos = criarDicionario(s);

				int maisLongoSufixo = 0;
				for (Entry<String, Integer> entry : prefixos.entrySet()) {
					Integer sufix = sufixos.get(entry.getKey());

					if (sufix != null && maisLongoSufixo < sufix) {
						maisLongoSufixo = sufix;
					}
				}
					
				result.add(maisLongoSufixo);
			}
			System.out.println(result.size());
		}
	}

	// retorna um set não considerando as strings repetidas.. pois terao o mesmo
	// numero magico
	private static Set<String> lerStrings(BufferedReader br, int n) throws IOException {
		Set<String> set = new HashSet<>();

		// não considero as strings iguais
		for (int i = 0; i < n; i++) {
			set.add(br.readLine().trim());
		}
		return set;
	}
	
	static Map<String, Integer> criarDicionario(String s) {
		Map<String, Integer> map = new HashMap<>();

		for (int i = 0; i < s.length(); i++) {
			for (int j = i + 1; j <= s.length(); j++) {

				String s2 = s.substring(i, j);
				map.put(s2, s2.length());
			}
		}
		return map;
	}
}
