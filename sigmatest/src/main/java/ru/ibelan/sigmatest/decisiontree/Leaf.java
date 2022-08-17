package ru.ibelan.sigmatest.decisiontree;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class Leaf extends Node {
	private String subject;
}
