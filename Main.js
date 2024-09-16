const fs = require('fs');

function parseInput(jsonInput) {
  const data = JSON.parse(jsonInput);
  const { n, k } = data.keys;
  const points = [];

  for (let i = 1; i <= n; i++) {
    if (data[i]) {
      const x = parseInt(i);
      const y = parseInt(data[i].value, parseInt(data[i].base));
      points.push({ x, y });
    }
  }

  return { n, k, points };
}

function lagrangeInterpolation(points, k) {
  const secret = points.slice(0, k).reduce((acc, point, i) => {
    let li = point.y;
    for (let j = 0; j < k; j++) {
      if (i !== j) {
        li *= points[j].x / (points[j].x - point.x);
      }
    }
    return acc + li;
  }, 0);

  return Math.round(secret);
}

function solveSecretSharing(jsonInput) {
  const { n, k, points } = parseInput(jsonInput);

  if (points.length < k) {
    throw new Error(Not enough points provided. Need at least ${k} points.);
  }

  const secret = lagrangeInterpolation(points, k);
  return secret;
}

// Test cases
const testCases = [
  {
    name: "Test Case 1",
    input: `
    {
      "keys": {
        "n": 4,
        "k": 3
      },
      "1": {
        "base": "10",
        "value": "4"
      },
      "2": {
        "base": "2",
        "value": "111"
      },
      "3": {
        "base": "10",
        "value": "12"
      },
      "6": {
        "base": "4",
        "value": "213"
      }
    }
    `
  },
  {
    name: "Test Case 2",
    input: `
    {
      "keys": {
        "n": 5,
        "k": 4
      },
      "1": {
        "base": "16",
        "value": "A"
      },
      "2": {
        "base": "2",
        "value": "1010"
      },
      "3": {
        "base": "8",
        "value": "15"
      },
      "4": {
        "base": "10",
        "value": "20"
      },
      "5": {
        "base": "3",
        "value": "202"
      }
    }
    `
  }
];
testCases.forEach((testCase, index) => {
  console.log(Running ${testCase.name}:);
  try {
    const secret = solveSecretSharing(testCase.input);
    console.log(The secret (constant term) is: ${secret});
  } catch (error) {
    console.error(Error in ${testCase.name}:, error.message);
  }
  console.log('---');
});

function runCustomTestCase(filePath) {
  try {
    const jsonInput = fs.readFileSync(filePath, 'utf8');
    const secret = solveSecretSharing(jsonInput);
    console.log(Custom Test Case (${filePath}):);
    console.log(The secret (constant term) is: ${secret});
  } catch (error) {
    console.error(Error in Custom Test Case (${filePath}):, error.message);
  }
}
