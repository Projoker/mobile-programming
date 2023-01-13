import React, {useState} from 'react';
import {Button, SafeAreaView, StyleSheet, Text} from 'react-native';

function App(): JSX.Element {
  const [labelText, setLabelText] = useState('Нажмите для получения даты');

  function click(): void {
    setLabelText(new Date().toDateString());
  }

  return (
    <SafeAreaView style={styles.container}>
      <Text style={styles.text}>{labelText}</Text>
      <Button title="Получить дату" onPress={click} />
    </SafeAreaView>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#fff',
    alignItems: 'center',
    justifyContent: 'center',
  },
  text: {
    bottom: 40,
  },
});

export default App;
